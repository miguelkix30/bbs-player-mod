package mchorse.bbs_mod.projects;

import mchorse.bbs_mod.data.types.BaseType;
import mchorse.bbs_mod.data.types.MapType;
import mchorse.bbs_mod.settings.values.ValueDouble;
import mchorse.bbs_mod.settings.values.ValueFloat;
import mchorse.bbs_mod.settings.values.ValueGroup;
import mchorse.bbs_mod.settings.values.ValueInt;
import mchorse.bbs_mod.settings.values.ValueString;
import mchorse.bbs_mod.settings.values.base.BaseValue;
import mchorse.bbs_mod.utils.DataPath;
import mchorse.bbs_mod.utils.keyframes.KeyframeChannel;
import mchorse.bbs_mod.utils.keyframes.factories.KeyframeFactories;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class KeyframeRepo extends ValueGroup
{
    private final static Map<Class, Function<String, KeyframeChannel>> maps = new HashMap<>();

    public final Map<String, KeyframeChannel> properties = new HashMap<>();

    static
    {
        maps.put(ValueInt.class, (key) -> new KeyframeChannel(key, KeyframeFactories.INTEGER));
        maps.put(ValueFloat.class, (key) -> new KeyframeChannel(key, KeyframeFactories.FLOAT));
        maps.put(ValueDouble.class, (key) -> new KeyframeChannel(key, KeyframeFactories.DOUBLE));
        maps.put(ValueString.class, (key) -> new KeyframeChannel(key, KeyframeFactories.STRING));
    }

    public KeyframeRepo(String id)
    {
        super(id);
    }

    public KeyframeChannel getOrCreate(BaseValue value)
    {
        DataPath path = value.getPath();

        path.strings.remove(0);

        String key = path.toString();
        KeyframeChannel channel = this.properties.get(key);

        return channel == null ? this.create(channel, key) : channel;
    }

    public KeyframeChannel create(BaseValue property, String key)
    {
        Function<String, KeyframeChannel> factory = maps.get(property.getClass());

        if (factory != null)
        {
            KeyframeChannel channel = factory.apply(key);

            this.properties.put(key, channel);
            this.add(channel);

            return channel;
        }

        return null;
    }

    @Override
    public void fromData(BaseType data)
    {
        super.fromData(data);

        this.properties.clear();

        if (!data.isMap())
        {
            return;
        }

        MapType map = data.asMap();

        for (String key : map.keys())
        {
            MapType mapType = map.getMap(key);

            if (mapType.isEmpty())
            {
                continue;
            }

            KeyframeChannel property = new KeyframeChannel(key, null);

            property.fromData(mapType);

            if (property.getFactory() != null)
            {
                this.properties.put(key, property);
                this.add(property);
            }
        }
    }
}