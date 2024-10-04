package leetcode

class Twitter() {
    private val followersOf = mutableMapOf<Int, MutableSet<Int>>()
    private val feedOf = mutableMapOf<Int, ArrayDeque<Int>>()
    private val tweets = mutableListOf<Pair</* userId */Int, /* tweetId */ Int>>()

    fun postTweet(userId: Int, tweetId: Int) {
        tweets.add(Pair(userId, tweetId))
        for (followerId in followersOf.getOrPut(userId) { mutableSetOf(userId) }) {
            val feed = feedOf.getOrPut(followerId) { ArrayDeque() }
            feed.add(tweetId)
            if (feed.size > 10) feed.removeFirst()
        }
    }

    fun getNewsFeed(userId: Int): List<Int> {
        return feedOf.getOrPut(userId) { ArrayDeque() }.asReversed()
    }

    fun follow(followerId: Int, followeeId: Int) {
        followersOf.getOrPut(followeeId) { mutableSetOf(followeeId) }.add(followerId)
        recomputeFeed(followerId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        followersOf.getOrPut(followeeId) { mutableSetOf(followeeId) }.remove(followerId)
        recomputeFeed(followerId)
    }

    private fun recomputeFeed(userId: Int) {
        val feed = ArrayDeque<Int>()
        for ((authorId, tweetId) in tweets.asReversed()) {
            if (followersOf.getOrPut(authorId) { mutableSetOf(authorId) }.contains(userId)) {
                feed.addFirst(tweetId)
                if (feed.size >= 10) break
            }
        }

        feedOf[userId] = feed
    }
}
