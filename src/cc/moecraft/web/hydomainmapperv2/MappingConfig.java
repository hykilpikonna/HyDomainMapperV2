package cc.moecraft.web.hydomainmapperv2;

import cc.moecraft.api.Config;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;

import static cc.moecraft.web.hydomainmapperv2.Main.VERSION;

/**
 * 此类由 Hykilpikonna 在 2018/04/19 创建!
 * Created by Hykilpikonna on 2018/04/19!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class MappingConfig extends Config
{
    private Map<String, MappingRule> mappingMapLolololol = new HashMap<>();

    private static final String RULES_PREFIX = "MappingRules.";

    public MappingConfig()
    {
        super(VERSION, "HyDomainMapperV2", "MappingConfig", "yml", false, true, true);
        System.out.println("HyDomainWrapperV2 Config Path = " + getConfigFile().getAbsolutePath());
    }

    public MappingRule getMappingRule(String from)
    {
        if (mappingMapLolololol.containsKey(from))
        {
            return mappingMapLolololol.get(from);
        }
        else
        {
            String configPath = RULES_PREFIX + from + ".";

            if (contains(configPath))
            {
                MappingRule result = new MappingRule(from, getString(configPath + "MapTo"), MappingMethod.valueOf(getString(configPath + "Method")));
                mappingMapLolololol.put(from, result);
                return result;
            }
            else
            {
                return MappingRule.MAPPING_RULE_NOT_EXIST;
            }
        }
    }

    @Override
    public void readConfig()
    {

    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault(RULES_PREFIX + "localhost.MapTo", "http://bing.com/");
        addDefault(RULES_PREFIX + "localhost.Method", MappingMethod.REDIRECT.name());
        addDefault(RULES_PREFIX + "127`0`0`1.MapTo", "http://www.bing.com/");
        addDefault(RULES_PREFIX + "127`0`0`1.Method", MappingMethod.CORS.name());
    }

    public Map<String, MappingRule> getMappingMap()
    {
        return mappingMapLolololol;
    }

    public void setMappingMap(Map<String, MappingRule> mappingMap)
    {
        this.mappingMapLolololol = mappingMap;
    }
}
