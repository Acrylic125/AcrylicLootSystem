package com.acrylic.universalloot.pseudoloot;

import com.acrylic.universalloot.Loot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public interface PseudoLoot<T extends Loot> extends Iterable<T> {

    List<T> getLoots();

    default void addLoot(@NotNull T loot) {
        getLoots().add(loot);
    }

    @Nullable
    default T getLoot(@NotNull String name) {
        Pattern pattern = Pattern.compile(name);
        for (T loot : getLoots()) {
            if (pattern.matcher(loot.getName()).matches())
                return loot;
        }
        return null;
    }

    @NotNull
    @Override
    default Iterator<T> iterator() {
        return getLoots().iterator();
    }

    @Override
    default void forEach(Consumer<? super T> action) {
        getLoots().forEach(action);
    }

    @Override
    default Spliterator<T> spliterator() {
        return getLoots().spliterator();
    }
}
