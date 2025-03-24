package mchorse.bbs_mod.projects;

import mchorse.bbs_mod.data.types.MapType;
import mchorse.bbs_mod.utils.manager.BaseManager;
import mchorse.bbs_mod.utils.manager.storage.CompressedDataStorage;

import java.io.File;
import java.util.function.Supplier;

public class ProjectManager extends BaseManager<Project>
{
    public ProjectManager(Supplier<File> folder)
    {
        super(folder);

        this.storage = new CompressedDataStorage();
    }

    @Override
    protected Project createData(String id, MapType mapType)
    {
        Project project = new Project();

        if (mapType != null)
        {
            project.fromData(mapType);
        }

        return project;
    }

    @Override
    protected String getExtension()
    {
        return ".dat";
    }
}