package mchorse.bbs_mod.utils.repos;

import mchorse.bbs_mod.data.types.MapType;
import mchorse.bbs_mod.film.Film;
import mchorse.bbs_mod.network.ClientNetwork;
import mchorse.bbs_mod.projects.Project;

import java.util.function.Consumer;

public class ProjectRepository extends NetworkRepository<Project>
{
    public ProjectRepository(String type)
    {
        super(type);
    }

    @Override
    public Project create(String id, MapType data)
    {
        Project film = new Project();

        film.setId(id);

        if (data != null)
        {
            film.fromData(data);
        }

        return film;
    }

    @Override
    public void load(String id, Consumer<Project> callback)
    {
        ClientNetwork.sendManagerDataLoad(this.type, id, (data) ->
        {
            if (data.isMap())
            {
                callback.accept(this.create(id, data.asMap()));
            }
        });
    }
}