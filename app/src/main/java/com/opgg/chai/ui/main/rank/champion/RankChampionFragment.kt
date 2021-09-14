package com.opgg.chai.ui.main.rank.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentRankChampionBinding
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.model.data.CompareCategoryItem
import com.opgg.chai.ui.main.rank.adapters.RankCompareChampionAdapter
import com.opgg.chai.ui.main.rank.adapters.decoration.VerticalSpaceItemDecoration
import com.opgg.chai.util.extension.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankChampionFragment: Fragment() {

    private val vm: RankChampionViewModel by viewModels()
    private lateinit var binding: FragmentRankChampionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rank_champion, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fragment = this@RankChampionFragment
            vm = this.vm
            adapter = RankCompareChampionAdapter()
            rankChampionResultList.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen._8dp)))
        }
        val choseChampion = arguments?.getParcelable<ChampionItem>("champion")

        vm.choseChampion = choseChampion

        choseChampion?.let {
            binding.rankChampionName.text = "${it.name} 랭킹"
            binding.rankChampionImage.loadImage(it.bigImage)
        }
        subscribeObserver()
        vm.loadCompareCategories()

        binding.rankChampionCategoryGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            if(radioGroup.findViewById<RadioButton>(checkedId) != null) {
                val button = radioGroup.findViewById<RadioButton>(checkedId)
                if(button.tag != null && button.tag is CompareCategoryItem) {
                   vm.choseCompareCategory = button.tag as CompareCategoryItem
                }
            }
        }
    }

    private fun subscribeObserver() {
        vm.compareCategories.observe(viewLifecycleOwner) {
            val params = RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT)
            params.marginStart = resources.getDimensionPixelSize(R.dimen._4dp)
            params.marginEnd = resources.getDimensionPixelSize(R.dimen._4dp)
            it.forEach {
                val item = RadioButton(requireContext()).apply {
                    text = it.name
                    tag = it
                    setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.selector_compare_category_tab))
                    setBackgroundResource(R.drawable.bg_compare_category_tab)
                    buttonDrawable = null
                    setPadding(
                        resources.getDimensionPixelSize(R.dimen._12dp),
                        resources.getDimensionPixelSize(R.dimen._8dp),
                        resources.getDimensionPixelSize(R.dimen._12dp),
                        resources.getDimensionPixelSize(R.dimen._8dp))
                    layoutParams = params
                }
                binding.rankChampionCategoryGroup.addView(item)
            }

            for(child in binding.rankChampionCategoryGroup.children) {
                if(child is RadioButton) {
                    if(child.text == arguments?.getParcelable<CompareCategoryItem>("compareCategory")?.name) {
                        child.isChecked = true
                        binding.rankChampionScroll.postDelayed({
                            binding.rankChampionScroll.scrollTo(child.x.toInt(), 0)
                        }, 100)
                        break
                    }
                }
            }
        }

        vm.rankItem.observe(viewLifecycleOwner) {
            binding.adapter?.submitList(it)
        }

        vm.progress.observe(viewLifecycleOwner) {
            binding.progress.visibility = if(it) View.VISIBLE else View.GONE

        }
    }

    fun onCloseClick(view: View) {
        view.findNavController().navigate(R.id.action_rankChampionFragment_to_rankFragment)
    }
}
