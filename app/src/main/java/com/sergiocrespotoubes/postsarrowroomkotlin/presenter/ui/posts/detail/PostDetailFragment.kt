package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts.detail

/**
 * Created by Sergio Crespo Toubes on 14/01/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import arrow.fx.extensions.io.async.effectMap
import arrow.fx.handleError
import coil.api.load
import com.jakewharton.rxbinding3.view.clicks
import com.saludonnet.pacientes.R
import com.saludonnet.pacientes.data.network.context.surgery.ServicesGroupService
import com.saludonnet.pacientes.data.network.models.ServicesGroupDetail
import com.saludonnet.pacientes.data.network.models.ServiceGroup
import com.saludonnet.pacientes.managers.ViewUtils
import com.saludonnet.pacientes.managers.gone
import com.saludonnet.pacientes.ui.base.BaseFragment
import com.saludonnet.pacientes.ui.base.BaseViewModel
import com.saludonnet.pacientes.ui.services_group.ServicesGroupActivity
import com.saludonnet.pacientes.ui.services_group.provinces_list.ServicesGroupProvincesListModel
import com.saludonnet.pacientes.util.Resource2
import com.saludonnet.pacientes.util.SpecialtyId
import com.saludonnet.pacientes.util.SpecialtyName
import com.saludonnet.pacientes.util.live_data.Event
import com.saludonnet.pacientes.util.live_data.EventObserver
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import de.mateware.snacky.Snacky
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_services_group_detail.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

@Parcelize
data class ServicesGroupDetailModel(
    val specialtyName: SpecialtyName,
    val specialtyId: SpecialtyId,
    val serviceGroup: ServiceGroup
) : Parcelable

class ServicesGroupDetailViewModel(
    val model: ServicesGroupDetailModel,
    val servicesGroupService: ServicesGroupService
) : BaseViewModel() {

    val surgeryResourceObs = MutableLiveData<Resource2<ServicesGroupDetail, Throwable>>()
    val loadProvincesListObs = MutableLiveData<Event<ServicesGroupProvincesListModel>>()

    fun onResume() {
        findSurgeryServiceBySurgerySpecialty()
    }

    private fun findSurgeryServiceBySurgerySpecialty() {
        servicesGroupService.findServicesGroupById(model.serviceGroup.id).continueOn(Dispatchers.Main)
            .effectMap { surgeryDetail ->
                surgeryResourceObs.value = Resource2.success(surgeryDetail)
            }.handleError {
                surgeryResourceObs.value = Resource2.error(it)
            }.unsafeRunAsync {}
    }

    fun selectProvinceClick() {
        loadProvincesListObs.value = Event(ServicesGroupProvincesListModel(
            specialtyName = model.specialtyName,
            specialtyId = model.specialtyId,
            serviceGroup = model.serviceGroup
        ))
    }
}

class ServicesGroupDetailFragment : BaseFragment() {

    private lateinit var vModel: ServicesGroupDetailViewModel
    val args: ServicesGroupDetailFragmentArgs by navArgs()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_services_group_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = args.servicesGroupDetailModel
        vModel = get { parametersOf(model) }

        loadViews(model.serviceGroup.name)
        loadObservers()
    }

    private fun loadViews(surgeryName: String) {
        (requireActivity() as ServicesGroupActivity).supportActionBar?.title = surgeryName

        continueButton.clicks().subscribe {
            vModel.selectProvinceClick()
        }
        surgeriesTypesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
    }

    private fun loadObservers() {
        vModel.surgeryResourceObs.observe(requireActivity(), Observer {
            loadSurgeryResource(it)
        })
        vModel.loadProvincesListObs.observe(requireActivity(), EventObserver {
            loadProvincesList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        vModel.onResume()
    }

    private fun loadSurgeryResource(servicesGroupDetailResource: Resource2<ServicesGroupDetail, Throwable>) {
        when (servicesGroupDetailResource) {
            is Resource2.Success -> { loadSurgery(servicesGroupDetailResource.value) }
            is Resource2.Error -> {
                Snacky.builder()
                    .setView(view)
                    .setText(R.string.generic_error_message)
                    .setDuration(Snacky.LENGTH_INDEFINITE)
                    .setActionText(android.R.string.ok)
                    .error()
                    .show()
            }
        }
    }

    private fun loadSurgery(servicesGroupDetail: ServicesGroupDetail) {
        (requireActivity() as ServicesGroupActivity).supportActionBar?.title = servicesGroupDetail.name

        surgeryBackground.load(servicesGroupDetail.pictureUrl) {
            crossfade(true)
            placeholder(R.color.colorGreyIcons)
            error(R.drawable.nophoto1)
        }
        surgeryTitle.text = servicesGroupDetail.name

        loadWhatIsItDescription(
            servicesGroupDetail.descriptions.whatIsIt,
            servicesGroupDetail.descriptions.whatIsItPictureUrl)
        loadSymptomsDescription(
            servicesGroupDetail.descriptions.symptoms,
            servicesGroupDetail.descriptions.symptomsPictureUrl)
        loadDiagnosisDescription(
            servicesGroupDetail.descriptions.diagnosis,
            servicesGroupDetail.descriptions.diagnosisPictureUrl)
        loadProcedureDescription(
            servicesGroupDetail.descriptions.procedure,
            servicesGroupDetail.descriptions.procedurePictureUrl)
        loadPostoperativeDescription(
            servicesGroupDetail.descriptions.postoperative,
            servicesGroupDetail.descriptions.postoperativePictureUrl)
        loadIndicationsDescription(
            servicesGroupDetail.descriptions.indications,
            servicesGroupDetail.descriptions.indicationsPictureUrl)
        loadTreatmentDescription(
            servicesGroupDetail.descriptions.treatment,
            servicesGroupDetail.descriptions.treatmentPictureUrl)

        priceText.text = ViewUtils.priceFormat(servicesGroupDetail.price)

        loadSurgeryTypes(servicesGroupDetail.services)
    }

    private fun loadWhatIsItDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_what_is_it.text = ViewUtils.fromHtml(description)
        } ?: tv_what_is_it.gone()
        ViewUtils.justificationMode(tv_what_is_it)

        imageUrl?.let {
            iv_what_is_it.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_what_is_it.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_what_is_it.gone()
        }
    }

    private fun loadSymptomsDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_symptoms.text = ViewUtils.fromHtml(description)
        } ?: tv_symptoms.gone()
        ViewUtils.justificationMode(tv_symptoms)

        imageUrl?.let {
            iv_symptoms.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_symptoms.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_symptoms.gone()
        }
    }

    private fun loadDiagnosisDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_diagnosis.text = ViewUtils.fromHtml(description)
        } ?: tv_diagnosis.gone()
        ViewUtils.justificationMode(tv_diagnosis)

        imageUrl?.let {
            iv_diagnosis.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_diagnosis.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_diagnosis.gone()
        }
    }

    private fun loadProcedureDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_procedure.text = ViewUtils.fromHtml(description)
        } ?: tv_procedure.gone()
        ViewUtils.justificationMode(tv_procedure)

        imageUrl?.let {
            iv_procedure.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_procedure.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_procedure.gone()
        }
    }

    private fun loadPostoperativeDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_postoperative.text = ViewUtils.fromHtml(description)
        } ?: tv_postoperative.gone()
        ViewUtils.justificationMode(tv_postoperative)

        imageUrl?.let {
            iv_postoperative.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_postoperative.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_postoperative.gone()
        }
    }

    private fun loadIndicationsDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_indications.text = ViewUtils.fromHtml(description)
        } ?: tv_indications.gone()
        ViewUtils.justificationMode(tv_indications)

        imageUrl?.let {
            iv_indications.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_indications.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_indications.gone()
        }
    }

    private fun loadTreatmentDescription(description: String?, imageUrl: String?) {
        description?.let {
            tv_treatment.text = ViewUtils.fromHtml(description)
        } ?: tv_treatment.gone()
        ViewUtils.justificationMode(tv_treatment)

        imageUrl?.let {
            iv_treatment.load(imageUrl) {
                placeholder(R.drawable.ic_no_doctor_vector)
                error(R.drawable.ic_no_doctor_vector)
            }
        } ?: iv_treatment.gone()

        if (description == null && imageUrl == null) {
            sonsubtitle_treatment.gone()
        }
    }

    private fun loadSurgeryTypes(servicesGroupDetailTypes: List<ServicesGroupDetail.SurgeryType>) {
        groupAdapter.clear()
        val surgeryTypesItems = servicesGroupDetailTypes.map {
            ServicesGroupTypeItem(it) {
                vModel.selectProvinceClick()
            }
        }
        groupAdapter.addAll(surgeryTypesItems)
    }

    private fun loadProvincesList(provincesListModel: ServicesGroupProvincesListModel) {
        val action =
            ServicesGroupDetailFragmentDirections.actionServicesGroupDetailFragmentToServicesGroupProvincesListFragment(
                provincesListModel
            )
        findNavController().navigate(action)
    }
}
