package mydataapi;

import java.util.List;

import ModeClasees.Emoji;

public interface OnEmojiClickListener {
    // Handle emoji click with the Emoji object
    void onEmojiClick(Emoji emoji);

    // Handle emoji fetched (e.g., from a specific section)
    void onEmojiFetched(List<Emoji> section3Emojis);

    // Handle all emojis fetched
    void onEmojisFetched(List<Emoji> emojis);

    // Handle emoji click with only emojiId
    void onEmojiClickWithId(int emojiId);

    // Handle emoji click associated with a wish
    void onEmojiClickForWish(int wishId, int emojiId);

    void onEmojiClick(int emojiId);

    void onEmojiClick(int wishId, int emojiId);
}
