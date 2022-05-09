package by.epam.web.logic;

import by.epam.web.entity.CalculableParameters;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

@Component
public class CopyHash {
    private final HashMap<CalculableParameters, Map<String, Object>> hashMap = new HashMap<>();

    public boolean isContain(CalculableParameters key) {
        return hashMap.containsKey(key);
    }

    public void addToMap(CalculableParameters key, Map<String, Object> result) {
        hashMap.put(key, result);
    }

    public Map<String, Object> getParameters(CalculableParameters key) {
        return hashMap.get(key);
    }
}
