package com.example.sportie.ui.team

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sportie.R
import com.example.sportie.base.BaseViewModelFragment
import com.example.sportie.databinding.FragmentTeamsBinding
import com.example.sportie.divider
import com.example.sportie.team
import kotlin.reflect.KClass

class TeamsFragment : BaseViewModelFragment<TeamsViewModel, FragmentTeamsBinding>() {

    override fun viewModelClass(): KClass<TeamsViewModel> = TeamsViewModel::class
    override fun layout(): Int = R.layout.fragment_teams

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.getTeams()

        vm.state.observe(viewLifecycleOwner) { state ->
            binding.rv.withModels {
                state.teams.forEach {
                    team {
                        id(it.id)
                        team(it)
                        onClick { _ ->
                            findNavController().navigate(
                                TeamsFragmentDirections.toTeamDetailsFragment(it.id),

                            )
                        }
                    }

                    divider {
                        id("divider${it.id}")
                        marginsHorizontal(16)
                    }
                }
            }
        }
    }
}