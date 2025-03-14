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

    public void split(UITab tab, int mouseX, int mouseY, ScrollDirection direction)
    {
        if (tab.getParent() instanceof UITabContainer container)
        {
            UITabContainer newContainer = new UITabContainer(this, new UITab(this), new UITab(this), direction);

            newContainer.tabResizer.enableDragging();

            if (container.a == tab)
            {
                container.replaceA(newContainer);
            }
            else
            {
                container.replaceB(newContainer);
            }

            newContainer.tabResizer.applyDragging(mouseX, mouseY);
            this.resize();
        }
    }
}