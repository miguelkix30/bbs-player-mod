package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.ScrollDirection;

public class UITabs extends UIElement
{
    public UIElement root;

    public UITabs()
    {
        this.root = new UITabContainer(this, new UITab(this), new UITab(this), ScrollDirection.VERTICAL);

        this.root.full(this);
        this.add(this.root);
    }

    public void join(UITabResizer resizer)
    {
        System.out.println("Join: " + resizer);
    }

    public void split(UITab tab, ScrollDirection direction)
    {
        System.out.println("Split: " + resizer);
    }
}