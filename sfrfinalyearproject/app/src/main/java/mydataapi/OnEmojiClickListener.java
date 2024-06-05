package mydataapi;

import java.util.List;

import ModeClasees.Emoji;

public interface OnEmojiClickListener {
    void onEmojiClick(Emoji emoji);

    void onEmojiFetched(List<Emoji> section3Emojis);

    void onEmojisFetched(List<Emoji> emojis);
}
