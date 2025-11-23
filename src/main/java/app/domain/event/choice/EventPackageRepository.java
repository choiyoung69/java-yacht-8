package app.domain.event.choice;

import com.fasterxml.jackson.databind.ObjectMapper;
import app.domain.event.environment.EventSource;
import java.io.File;
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

    private void loadAll(String baseDir) {
        for (EventSource source : EventSource.values()) {

            String dirPath = baseDir + "/" + source.name().toLowerCase();

            File dir = new File(dirPath);
            if (!dir.exists()) continue;

            File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
            if (files == null) continue;

            for (File file : files) {
                try {
                    EventPackage pkg = mapper.readValue(file, EventPackage.class);
                    packages.put(pkg.type(), pkg);
                } catch (Exception e) {
                    throw new RuntimeException("로드 실패: " + file.getName(), e);
                }
            }
        }
    }

    public EventPackage find(EnvironmentEventType type) {
        return packages.get(type);
    }
}

