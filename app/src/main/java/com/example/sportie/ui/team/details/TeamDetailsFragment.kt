package com.example.sportie.ui.team.details

import android.os.Bundle
import android.view.View
import com.example.sportie.R
import com.example.sportie.base.BaseViewModelFragment
import com.example.sportie.databinding.FragmentDetailsBinding
import com.example.sportie.detailsHeader
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

class TeamDetailsFragment : BaseViewModelFragment<TeamDetailsViewModel, FragmentDetailsBinding>() {
    override fun viewModelClass(): KClass<TeamDetailsViewModel> = TeamDetailsViewModel::class

    override fun layout(): Int = R.layout.fragment_details

    override val vm: TeamDetailsViewModel by viewModel {
        val id = TeamDetailsFragmentArgs.fromBundle(requireArguments()).id
        parametersOf(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val id = TeamDetailsFragmentArgs.fromBundle(requireArguments()).id
        vm.getTeamDetails(id)

        vm.state.observe(viewLifecycleOwner) { state ->
            binding.rv.withModels {
                detailsHeader {
                    id("header")
                    team(state.team)
                }
            }
        }
    }
}