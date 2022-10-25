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
                2
            )
        )
        assertFalse(result)
    }
}