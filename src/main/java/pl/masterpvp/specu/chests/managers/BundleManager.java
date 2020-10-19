package pl.masterpvp.specu.chests.managers;

import pl.masterpvp.specu.chests.bundle.Bundle;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BundleManager {

    private static Map<String, Bundle> map = new LinkedHashMap<>();

    public static void registerFiles(Bundle... bundles){
        Arrays.asList(bundles).forEach(bundle -> {
            getMap().put(bundle.getName(), bundle);
            bundle.init();
        });
    }

    public static Bundle getFile(String name){
        return getMap().get(name);
    }

    public static Map<String, Bundle> getMap() {
        return map;
    }
}
