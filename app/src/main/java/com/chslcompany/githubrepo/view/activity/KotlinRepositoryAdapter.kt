package com.chslcompany.githubrepo.view.activity

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
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.databinding.AdapterRepositoryBinding

class KotlinRepositoryAdapter() :
    ListAdapter<Item, KotlinRepositoryAdapter.KotlinRepositoryViewHolder>(DIFF_CALLBACK) {

    private lateinit var bindingAdapter: AdapterRepositoryBinding
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinRepositoryViewHolder {
        bindingAdapter =
            AdapterRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KotlinRepositoryViewHolder(bindingAdapter)
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

    class KotlinRepositoryViewHolder(private val bindingAdapter: AdapterRepositoryBinding) :
        RecyclerView.ViewHolder(bindingAdapter.root) {
        fun bind(item: Item) {
            bindingAdapter.tvNameRepository.text = item.full_name
            bindingAdapter.tvDescription.text = item.description
            bindingAdapter.tvCountFork.text = item.forks_count.toString()
            bindingAdapter.tvCountStar.text = item.stargazers_count.toString()
            bindingAdapter.tvNameUser.text = item.owner?.login
            bindingAdapter.tvNickName.text = item.name

            bindingAdapter.ivAvatar.alpha = 0.3f
            bindingAdapter.ivAvatar.animate().setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator()).alpha(1f)

            Glide.with(itemView)
                .load(item.owner?.avatar_url)
                .placeholder(R.drawable.ic_circle_account)
                .error(R.drawable.ic_circle_account)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(bindingAdapter.ivAvatar)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.html_url == newItem.html_url
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

}