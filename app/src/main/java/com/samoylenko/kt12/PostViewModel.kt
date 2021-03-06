package com.samoylenko.kt12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val empty = Post (
            id = 0,
            author = "0",
            content = "",
            published = "",
            sharing = 0,
            like = 0,
            countVisability = 0,
            likedByMe = false
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<List<Post>> = repository.getAll()
    val edited: MutableLiveData<Post> = MutableLiveData(empty)


    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun changeContent(content: String){
        val text = content.trim()
        if (edited.value?.content == text){
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun edit(post: Post){
        edited.value = post
    }
}