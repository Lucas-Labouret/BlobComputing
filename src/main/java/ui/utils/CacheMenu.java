package ui.utils;

import javafx.scene.control.ComboBox;
import language.cache.Cache;
import ui.InstructionPlayer;

public class CacheMenu extends ComboBox<CacheMenu.CacheItem> {
    public static class CacheItem {
        public final String name;
        public final Cache.CacheEntry entry;
        public CacheItem(Cache.CacheEntry entry) {
            this.name = String.valueOf(entry.stepCount);
            this.entry = entry;
        }

        protected CacheItem(String name) {
            this.name = name;
            this.entry = null;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private static class AddCacheItem extends CacheItem {
        public AddCacheItem(String name) { super(name); }
    }

    public CacheMenu(InstructionPlayer player) {
        this.getItems().add(new AddCacheItem("New Quicksave"));

        this.setOnAction((_) -> {
            CacheItem item = this.getValue();
            if (item instanceof AddCacheItem) {
                Cache.CacheEntry entry = player.saveState();
                this.getItems().add(new CacheItem(entry));
                this.setValue(this.getItems().getLast());
            }
        });
    }
}