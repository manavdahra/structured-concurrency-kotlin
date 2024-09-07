package org.example.exercise

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
)

@Serializable
data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)

@Serializable
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
)

class Page {
    private val users = mutableListOf<User>()
    private val posts = mutableListOf<Post>()
    private val comments = mutableListOf<Comment>()

    fun addUsers(users: List<User>) {
        this.users += users
    }

    fun addPosts(posts: List<Post>) {
        this.posts += posts
    }

    fun addComments(comments: List<Comment>) {
        this.comments += comments
    }

    fun clear() {
        users.clear()
        posts.clear()
        comments.clear()
    }

    override fun toString(): String {
        return """
            Users: [${users.size}]
            Posts: [${posts.size}]
            Comments: [${comments.size}]
        """.trimIndent()
    }
}
