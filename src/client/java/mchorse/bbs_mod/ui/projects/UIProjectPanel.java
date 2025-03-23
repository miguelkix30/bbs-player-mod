package mchorse.bbs_mod.ui.projects;

import mchorse.bbs_mod.ui.dashboard.UIDashboard;
import mchorse.bbs_mod.ui.dashboard.panels.UIDashboardPanel;
import mchorse.bbs_mod.ui.projects.tabs.UITabs;

public class UIProjectPanel extends UIDashboardPanel
{
    public UITabs tabs;

    public UIProjectPanel(UIDashboard dashboard)
    {
        super(dashboard);

        this.tabs = new UITabs(null);

        this.tabs.full(this);
        this.add(this.tabs);
    }
}