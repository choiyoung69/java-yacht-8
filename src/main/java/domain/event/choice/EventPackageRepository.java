package domain.event.choice;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import domain.event.environment.EnvironmentEventType;

public class EventPackageRepository {

    private final Map<EnvironmentEventType, EventPackage> packages =
            new EnumMap<>(EnvironmentEventType.class);

    private final ObjectMapper mapper = new ObjectMapper();

    public EventPackageRepository(String directoryPath) {
        loadAll(directoryPath);
    }

    private void loadAll(String directoryPath) {
        File dir = new File(directoryPath);

        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalArgumentException("유효하지 않은 경로입니다 " + directoryPath);
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
        if (files == null) return;

        for (File file : files) {
            try {
                EventPackage pkg = mapper.readValue(file, EventPackage.class);

                pkg.correct().init();
                pkg.wrong().init();

                packages.put(pkg.type(), pkg);

            } catch (Exception e) {
                throw new RuntimeException("이벤트 패키지 로드에 실패했습니다: " + file.getName(), e);
            }
        }
    }

    public EventPackage find(EnvironmentEventType type) {
        return packages.get(type);
    }
}

