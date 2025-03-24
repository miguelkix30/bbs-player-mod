package mchorse.bbs_mod.ui.projects;

import mchorse.bbs_mod.l10n.keys.IKey;
import mchorse.bbs_mod.projects.Project;
import mchorse.bbs_mod.ui.ContentType;
import mchorse.bbs_mod.ui.dashboard.UIDashboard;
import mchorse.bbs_mod.ui.dashboard.panels.UIDataDashboardPanel;
import mchorse.bbs_mod.ui.projects.tabs.UITabs;

public class UIProjectPanel extends UIDataDashboardPanel<Project>
{
    public UITabs tabs;

    public UIProjectPanel(UIDashboard dashboard)
    {
        super(dashboard);

        this.tabs = new UITabs(null);

        this.tabs.full(this.editor);
        this.editor.add(this.tabs);
    }

    @Override
    protected IKey getTitle()
    {
        return IKey.raw("Projects");
    }

    @Override
    public ContentType getType()
    {
        return ContentType.PROJECTS;
    }

    @Override
    protected void fillData(Project data)
    {

    }
}