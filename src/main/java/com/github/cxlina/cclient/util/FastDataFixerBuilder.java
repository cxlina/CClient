package com.github.cxlina.cclient.util;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class FastDataFixerBuilder extends DataFixerBuilder {

    public FastDataFixerBuilder(int dataVersion) {
        super(dataVersion);
    }

    @Override
    public DataFixer buildOptimized(Executor executor) {
        return super.buildOptimized(command -> {
        });
    }
}
