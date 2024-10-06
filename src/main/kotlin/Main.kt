package ru.netology

//абстрактный базовый класс с общими полями
abstract class Attachment(type: String)

//класс описывающий фото пост
data class Photo(val id: Int, val text: String, val width: Int, val height: Int)
data class AttachmentPhoto(val photo: Photo): Attachment("photo")

//класс описывающий аудио пост
data class Audio(val id: Int, val artist: String, val title: String, val duration: Int)
data class AttachmentAudio(val audio: Audio): Attachment("audio")

//класс описывающий видео пост
data class Video(val id: Int, val title: String, val description: String, val duration: Int)
data class AttachmentVideo(val video: Video): Attachment("video")

//класс описывающий файл пост
data class File(val id: Int, val title: String, val size: Int, val ext: String)
data class AttachmentFile(val file: File): Attachment("file")

//класс описывающий граффити пост
data class Graph(val id: Int, val url: String, val width: Int, val height: Int)
data class AttachmentGraph(val craph: Graph): Attachment("graph")

data class Likes(var count: Int = 0)

data class Comment (var id_post: Int, val text: String)

class PostNotFoundException(message: String): RuntimeException(message)
class NoSuchPostException(message: String): RuntimeException(message)

data class Post(
    val id: Int,
    val text: String,
    val likes: Likes = Likes(),
    var attacments: Array<Attachment> = emptyArray()
)

fun main() {
    var post: Post
    val repost: Post
    val likes: Likes = Likes(100)
    post = Post(0, "Kotlin!!!", likes, arrayOf(AttachmentPhoto(Photo(1, "PHOTO", 100, 100)), AttachmentAudio(Audio(1, "Alla", "Music", 5))))
    WallService.add(post)
    post = Post(0, "Kotlin good!!!", likes, arrayOf(AttachmentAudio(Audio(1, "Alla", "Music", 5))))
    WallService.add(post)
    WallService.printPosts()
    val comment: Comment?
    comment = Comment(0, "Привет!!!")
    val com = WallService.createComment(100, comment)
}

object WallService {

    var posts = emptyArray<Post>()
    var comments = emptyArray<Comment>()
    private var lastId = 0

    fun createComment(postId: Int, comment: Comment): Comment? {
        for (post in posts)
            if (postId == post.id) {
                comment.id_post = postId
                comments += comment
                return comment
            }
        throw NoSuchPostException("ERROR")//создаем исключение, если коммент не создан
    }

    fun add(post: Post): Boolean {
        posts += post.copy(id = ++lastId)
        //return posts.last()
        return true;
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (posts[index].id == newPost.id) {
                posts[index] = newPost
                return true
            }
        }
        return false
    }
    fun printPosts() {
        for (post in posts) {
            print (post)
            println()
        }
        println()
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
}