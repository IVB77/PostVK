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
    val attachments: Array<Attachment>?,
    var id: Int = 0
)

interface Attachment {
    val type: String
}

class VideoAttachment(
    private val id: Int,
    private val ownerId: Int,
    private val title: String,
    private val description: String,
    private val duration: Int,
    override val type: String = "Video"
) : Attachment {
    override fun toString(): String {

        return "$type $id $ownerId $title $description $duration"
    }
}

class AudioAttachment(
    private val id: Int,
    private val ownerId: Int,
    private val artist: String,
    private val title: String,
    private val duration: Int,
    override val type: String = "Audio"
) : Attachment {
    override fun toString(): String {

        return "$type $id $ownerId $artist $title $duration"
    }
}

class PhotoAttachment(
    private val id: Int,
    private val albumId: Int,
    private val ownerId: Int,
    private val userId: Int,
    private val text: String,
    private val date: Int,
    override val type: String = "Photo"
) : Attachment {
    override fun toString(): String {

        return "$type $id $albumId $ownerId $userId $text $date"
    }
}

class LinkAttachment(
    private val url: String,
    private val title: String,
    private val caption: String,
    private val description: String,
    private val previewPage: String,
    override val type: String = "Link"
) : Attachment {
    override fun toString(): String {

        return "$type $url $title $caption $description $previewPage"
    }
}

class DocAttachment(
    private val id: Int,
    private val ownerId: Int,
    private val title: String,
    private val size: Int,
    private val ext: String,
    private val date: Int,
    override val type: String = "Doc"
) : Attachment {
    override fun toString(): String {

        return "$type $id $ownerId $title $size $ext $date"
    }
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
                    copyHistory = post.copyHistory,
                    attachments = post.attachments
                )
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        var str = ""
        for (post in posts) {
            str += post.toString() + "\n"
        }
        return str
    }
}

fun main() {
    var attachment: Array<Attachment> = emptyArray()
    attachment += VideoAttachment(1, 2, "Vij", "good", 10)
    attachment += AudioAttachment(2, 2, "Sting", "Song", 15)
    WallService.add(Post(2, 3, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), attachment))

    attachment = emptyArray()
    attachment += VideoAttachment(1, 2, "Blood", "not good", 10)
    attachment += AudioAttachment(2, 2, "Coj", "Song2", 15)
    WallService.add(Post(8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338), attachment))

    attachment = emptyArray()
    attachment += LinkAttachment("aks-com.ru", "Electricdesign", "caption", "description", "previewPage")
    attachment += DocAttachment(5, 6, "Book", 20, "doc", 123)
    WallService.add(Post(18, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338), attachment))

    attachment = emptyArray()
    attachment += PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120)
    WallService.add(Post(11, null, null, null, null, "111", null, true, Views(13), null, attachment))

    print(WallService.toString())
    println()

    WallService.update(Post(2222, 3222, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), null, 2))
    WallService.update(Post(2222, 3222, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), attachment, 5))

    attachment = emptyArray()
    attachment += VideoAttachment(1, 2, "Blood", "not good", 10)
    attachment += AudioAttachment(2, 2, "Coj", "Song2", 15)
    WallService.update(Post(2222, 3222, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220), attachment, 4))
    print(WallService.toString())

}