package com.chslcompany.githubrepo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chslcompany.githubrepo.databinding.AdapterRepositoryBinding
import com.chslcompany.githubrepo.network.model.Item

class RepositoryAdapter(private val repoList: MutableList<Item>, private val context: Context) :
RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private lateinit var bindingAdapter : AdapterRepositoryBinding
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        bindingAdapter = AdapterRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RepositoryViewHolder(bindingAdapter)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repoList[position], context)
        animation(holder.itemView,position)
    }

    override fun getItemCount() = repoList.size

    private fun animation(view: View, position: Int){
        lastPosition = if (position > lastPosition){
            val animation = AnimationUtils.loadAnimation(view.context,android.R.anim.slide_in_left)
            view.startAnimation(animation)
            position
        } else{
            val animation = AnimationUtils.loadAnimation(view.context,android.R.anim.slide_out_right)
            view.startAnimation(animation)
            position
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(repositories: List<Item>) {
        if (repositories.isNullOrEmpty().not()) {
            repoList.clear()
            repoList.addAll(repositories)
            notifyDataSetChanged()
        }
    }

    class RepositoryViewHolder(private val bindingAdapter : AdapterRepositoryBinding)
        : RecyclerView.ViewHolder(bindingAdapter.root) {
        fun bind(item: Item, context: Context) {
            bindingAdapter.tvNameRepository.text = item.full_name
            bindingAdapter.tvDescription.text = item.description
            bindingAdapter.tvCountFork.text = item.forks_count.toString()
            bindingAdapter.tvCountStar.text = item.stargazers_count.toString()
            bindingAdapter.tvNameUser.text = item.owner?.login
            bindingAdapter.tvNickName.text = item.name

            bindingAdapter.ivAvatar.alpha = 0.3f
            bindingAdapter.ivAvatar.animate().setDuration(400).setInterpolator(AccelerateDecelerateInterpolator()).alpha(1f)

            Glide.with(context)
                .load(item.owner?.avatar_url)
                .placeholder(R.drawable.ic_circle_account)
                .error(R.drawable.ic_circle_account)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(bindingAdapter.ivAvatar)
        }

    }
}