import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun add() {
        val id = WallService.add(
            Post(
                2, 3, 4, 5, 6, "5", "6", false, Views(20), arrayOf(110, 220),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        ).id
        assertTrue(id > 0)
    }

    @Test
    fun updateTrue() {
        WallService.add(
            Post(
                8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        )
        val result = WallService.update(
            Post(
                2222,
                3222,
                4,
                5,
                6,
                "5",
                "6",
                false,
                Views(20),
                arrayOf(110, 220),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120)),
                1
            )
        )
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        WallService.add(
            Post(
                8,
                9,
                10,
                7,
                8,
                "11",
                "12",
                false,
                Views(10),
                arrayOf(115, 225, 338),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        )
        val result = WallService.update(
            Post(
                2222,
                3222,
                4,
                5,
                6,
                "5",
                "6",
                false,
                Views(20),
                arrayOf(110, 220),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120)),
                0
            )
        )
        assertFalse(result)
    }

    @Test
    fun createCommentsTrue() {
        WallService.add(
            Post(
                8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        )
        val result = WallService.createComment(1, Comment(2, 3, "comment", 4))
        assert(result.id != 0)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentsException() {
        val result: Comment =
            WallService.createComment(6, Comment(2, 3, "comment", 4))
    }

    @Test
    fun createReportTrue() {
        WallService.add(
            Post(
                8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        )
        WallService.createComment(1, Comment(2, 3, "comment", 4))
        val report: Report = WallService.createReportComment(Report(5, 1, 5))
        assert(report.ownerId == 5 && report.commentId == 1 && report.reason == 5)
    }

    @Test(expected = CommentNotFound::class)
    fun createReportExceptionCommentId() {
        WallService.add(
            Post(
                8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        )
        WallService.createComment(1, Comment(2, 3, "comment", 4))
        var report: Report = WallService.createReportComment(Report(5, 0, 5))
    }

    @Test(expected = CommentNotFound::class)
    fun createReportExceptionReason() {
        WallService.add(
            Post(
                8, 9, 10, 7, 8, "11", "12", false, Views(10), arrayOf(115, 225, 338),
                arrayOf(PhotoAttachment(1, 2, 7, 20, "Photo of forest", 120))
            )
        )
        WallService.createComment(1, Comment(2, 3, "comment", 4))
        var report: Report = WallService.createReportComment(Report(5, 1, 7))
    }
}