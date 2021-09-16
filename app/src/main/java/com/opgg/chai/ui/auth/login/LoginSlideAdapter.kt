package com.opgg.chai.ui.auth.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemIntroBinding

class LoginSlideAdapter: RecyclerView.Adapter<LoginSlideAdapter.LoginSlideVH>() {
    //로그인 인트로 소개 페이지
    private val images = listOf(R.drawable.intro_1, R.drawable.intro_2, R.drawable.intro_3)

    inner class LoginSlideVH(val binding: ItemIntroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(imageId: Int) {
            Glide.with(itemView).load(imageId).into(binding.introImg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginSlideVH {
        val binding = DataBindingUtil.inflate<ItemIntroBinding>(LayoutInflater.from(parent.context), R.layout.item_intro, parent, false)

        return LoginSlideVH(binding)
    }

    override fun onBindViewHolder(holder: LoginSlideVH, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}