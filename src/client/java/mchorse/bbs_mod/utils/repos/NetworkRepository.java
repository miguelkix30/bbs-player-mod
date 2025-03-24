package mchorse.bbs_mod.utils.repos;

import mchorse.bbs_mod.data.types.BaseType;
import mchorse.bbs_mod.data.types.MapType;
import mchorse.bbs_mod.network.ClientNetwork;
import mchorse.bbs_mod.settings.values.ValueGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public abstract class NetworkRepository <T extends ValueGroup> implements IRepository<T>
{
    protected final String type;

    public NetworkRepository(String type)
    {
        this.type = type;
    }

    @Override
    public void save(String id, MapType data)
    {
        MapType mapType = new MapType();

        mapType.putString("id", id);
        mapType.put("data", data);

        ClientNetwork.sendManagerData(this.type, -1, RepositoryOperation.SAVE, mapType);
    }

    @Override
    public void rename(String id, String name)
    {
        MapType mapType = new MapType();

        mapType.putString("from", id);
        mapType.putString("to", name);

        ClientNetwork.sendManagerData(this.type, -1, RepositoryOperation.RENAME, mapType);
    }

    @Override
    public void delete(String id)
    {
        MapType mapType = new MapType();

        mapType.putString("id", id);

        ClientNetwork.sendManagerData(this.type, -1, RepositoryOperation.DELETE, mapType);
    }

    @Override
    public void requestKeys(Consumer<Collection<String>> callback)
    {
        MapType mapType = new MapType();

        ClientNetwork.sendManagerData(this.type, RepositoryOperation.KEYS, mapType, (data) ->
        {
            if (!data.isList())
            {
                return;
            }

            List<String> list = new ArrayList<>();

            for (BaseType element : data.asList())
            {
                list.add(element.asString());
            }

            callback.accept(list);
        });
    }

    @Override
    public File getFolder()
    {
        return null;
    }

    @Override
    public void addFolder(String path, Consumer<Boolean> callback)
    {
        MapType mapType = new MapType();

        mapType.putString("folder", path);

        ClientNetwork.sendManagerData(this.type, RepositoryOperation.ADD_FOLDER, mapType, (data) ->
        {
            if (data.isNumeric())
            {
                callback.accept(data.asNumeric().boolValue());
            }
        });
    }

    @Override
    public void renameFolder(String path, String name, Consumer<Boolean> callback)
    {
        MapType mapType = new MapType();

        mapType.putString("from", path);
        mapType.putString("to", name);

        ClientNetwork.sendManagerData(this.type, RepositoryOperation.RENAME_FOLDER, mapType, (data) ->
        {
            if (data.isNumeric())
            {
                callback.accept(data.asNumeric().boolValue());
            }
        });
    }

    @Override
    public void deleteFolder(String path, Consumer<Boolean> callback)
    {
        MapType mapType = new MapType();

        mapType.putString("folder", path);

        ClientNetwork.sendManagerData(this.type, RepositoryOperation.DELETE_FOLDER, mapType, (data) ->
        {
            if (data.isNumeric())
            {
                callback.accept(data.asNumeric().boolValue());
            }
        });
    }
}