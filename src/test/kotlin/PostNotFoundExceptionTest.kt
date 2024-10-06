import ru.netology.*
import org.junit.Assert.assertEquals
import org.junit.Test

class PostNotFoundExceptionTest {
    @Test(expected = NoSuchPostException::class)
    fun shouldThrow1() {
        var post: Post
        val likes: Likes = Likes(100)
        post = Post(0, "Kotlin!!!", likes, arrayOf(AttachmentPhoto(Photo(1, "PHOTO", 100, 100)), AttachmentAudio(Audio(1, "Alla", "Music", 5))))
        WallService.add(post)
        post = Post(0, "Kotlin good!!!", likes, arrayOf(AttachmentAudio(Audio(1, "Alla", "Music", 5))))
        WallService.add(post)
        WallService.printPosts()
        val comment: Comment?
        comment = Comment(0, "Привет!!!")
        //выкидывает исключение (значит тест прошел успешно), так как поста с id 100 нет
        WallService.createComment(100, comment)
    }

    @Test
    fun shouldThrow2() {
        var post: Post
        val likes: Likes = Likes(100)
        post = Post(0, "Kotlin!!!", likes, arrayOf(AttachmentPhoto(Photo(1, "PHOTO", 100, 100)), AttachmentAudio(Audio(1, "Alla", "Music", 5))))
        WallService.add(post)
        post = Post(0, "Kotlin good!!!", likes, arrayOf(AttachmentAudio(Audio(1, "Alla", "Music", 5))))
        WallService.add(post)
        WallService.printPosts()
        val comment: Comment?
        comment = Comment(0, "Привет!!!")
        //тест проходит успешно, так как assert проверила что коммент добавлен к существующему посту
        assertEquals(comment, WallService.createComment(1, comment))
    }
}