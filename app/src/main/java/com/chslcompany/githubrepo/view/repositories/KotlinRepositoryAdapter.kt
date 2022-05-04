package com.chslcompany.githubrepo.view.repositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chslcompany.githubrepo.R
import com.chslcompany.githubrepo.data.domain.ItemDomain
import com.chslcompany.githubrepo.databinding.AdapterRepositoryBinding

class KotlinRepositoryAdapter(private val onClick : ((itemDomain : ItemDomain) -> Unit)) :
    ListAdapter<ItemDomain, KotlinRepositoryAdapter.KotlinRepositoryViewHolder>(DIFF_CALLBACK) {

    private lateinit var bindingAdapter: AdapterRepositoryBinding
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinRepositoryViewHolder {
        bindingAdapter =
            AdapterRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KotlinRepositoryViewHolder(bindingAdapter, onClick)
    }

    override fun onBindViewHolder(holder: KotlinRepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        animation(holder.itemView, position)
    }

    private fun animation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(view.context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
            lastPosition = position
        }
    }

    class KotlinRepositoryViewHolder(private val bindingAdapter: AdapterRepositoryBinding,
          private val onClick : (itemDomain : ItemDomain)-> Unit ) :
        RecyclerView.ViewHolder(bindingAdapter.root) {

        fun bind(itemDomain: ItemDomain) {
            bindingAdapter.tvNameRepository.text = itemDomain.full_name_domain
            bindingAdapter.tvDescription.text = itemDomain.description_domain
            bindingAdapter.tvCountFork.text = itemDomain.forks_count_domain.toString()
            bindingAdapter.tvCountStar.text = itemDomain.stargazers_count_domain.toString()
            bindingAdapter.tvNameUser.text = itemDomain.login_domain
            bindingAdapter.tvNickName.text = itemDomain.name_domain

            bindingAdapter.ivAvatar.alpha = 0.3f
            bindingAdapter.ivAvatar.animate().setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator()).alpha(1f)

            Glide.with(itemView)
                .load(itemDomain.avatar_url_domain)
                .placeholder(R.drawable.ic_circle_account)
                .error(R.drawable.ic_circle_account)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(bindingAdapter.ivAvatar)

            itemView.setOnClickListener {
                onClick.invoke(itemDomain)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemDomain>() {
            override fun areItemsTheSame(oldItem: ItemDomain, newItem: ItemDomain): Boolean {
                return oldItem.html_url_domain == newItem.html_url_domain
            }

            override fun areContentsTheSame(oldItem: ItemDomain, newItem: ItemDomain): Boolean {
                return oldItem == newItem
            }
        }
    }

}