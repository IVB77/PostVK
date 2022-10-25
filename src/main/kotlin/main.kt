data class Post(
    val ownerId: Int,
    val fromId: Int?,
    val createdBy: Int?,
    val date: Int?,
    val replyOwnerId: Int?,
    val text: String,
    val copyright: String?,
    val friendsOnly: Boolean,
    val views: Views,
    val copyHistory: Array<Int>?,
    var id: Int = 0
) {

}

class Views(private val count: Int) {
    override fun toString(): String {
        return count.toString()
    }
}

object WallService {
    private var id: Int = 1
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += post
        posts.last().id = id
        id += 1
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postFind) in posts.withIndex()) {
            if (post.id == postFind.id) {
                posts[index] = postFind.copy(
                    fromId = post.fromId,
                    createdBy = post.createdBy,
                    replyOwnerId = post.replyOwnerId,
                    text = post.text,
                    copyright = post.copyright,
                    friendsOnly = post.friendsOnly,
                    views = post.views,
                    copyHistory = post.copyHistory
                )
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        var str: String = ""
        for (post in posts) {
            str += post.toString() + "\n"
        }
        return str
    }
}

fun main() {
    WallService.add(Post(2, 3, 4, 5, 6, "5" , "6", false, Views(20), arrayOf(110, 220)))
    WallService.add(Post(8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338)))
    WallService.add(Post(18, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338)))
    WallService.add(Post(11, null, null, null, null, "111", null, true, Views(13), null))
    print(WallService.toString())
    println()
    WallService.update(Post(2222, 3222, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), 2))
    WallService.update(Post(2222, 3222, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), 5))
    WallService.update(Post(2222, 3222, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), 4))
    print(WallService.toString())

}