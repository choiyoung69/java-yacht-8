package app.domain.event.choice;

import com.fasterxml.jackson.databind.ObjectMapper;
import app.domain.event.environment.EventSource;
import java.io.File;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import app.domain.event.environment.EnvironmentEventType;

public class EventPackageRepository {

    private final Map<EnvironmentEventType, EventPackage> packages =
            new EnumMap<>(EnvironmentEventType.class);

    private final ObjectMapper mapper = new ObjectMapper();

    public EventPackageRepository(String directoryPath) {
        loadAll(directoryPath);
    }

    private void loadAll(String basePath) {
        for (EventSource source : EventSource.values()) {
            String dirPath = basePath + "/" + source.name().toLowerCase();

            File dir = new File(dirPath);
            if (!dir.exists()) continue;
            try {
                File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
                if (files == null) continue;

                for (File file : files) {
                    EventPackage pkg = mapper.readValue(file, EventPackage.class);
                    packages.put(pkg.getType(), pkg);
                }

            } catch (Exception e) {
                throw new RuntimeException("리소스 로딩 오류: " + dirPath, e);
            }
        }
    }

    public EventPackage find(EnvironmentEventType type) {
        return packages.get(type);
    }
}

