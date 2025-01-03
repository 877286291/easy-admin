package top.houyuji.code.generator.core.handler.keywords;

import jakarta.validation.constraints.NotNull;
import top.houyuji.code.generator.core.handler.IKeyWordsHandler;

import java.util.*;

/**
 * 基类关键字处理
 */
public abstract class BaseKeyWordsHandler implements IKeyWordsHandler {
    public final Set<String> keyWords;

    public BaseKeyWordsHandler(@NotNull List<String> keyWords) {
        this.keyWords = new HashSet<>(keyWords);
    }

    public BaseKeyWordsHandler(@NotNull Set<String> keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public @NotNull Collection<String> getKeyWords() {
        return keyWords;
    }

    @Override
    public boolean isKeyWords(@NotNull String columnName) {
        return getKeyWords().contains(columnName.toUpperCase(Locale.ENGLISH));
    }

}
