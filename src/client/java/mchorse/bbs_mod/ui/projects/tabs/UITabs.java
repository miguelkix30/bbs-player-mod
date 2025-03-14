package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.ui.framework.UIContext;
import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.ScrollDirection;
import mchorse.bbs_mod.utils.colors.Colors;

public class UITabs extends UIElement
{
    public UIElement root;

    private boolean joining;
    private UITabContainer container;

    public UITabs()
    {
        this.root = new UITabContainer(this, new UITab(this), new UITab(this), ScrollDirection.VERTICAL);

        this.root.full(this);
        this.add(this.root);
    }

    public void join(UITabResizer resizer)
    {
        this.joining = true;
        this.container = resizer.container;
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

    @Override
    protected boolean subMouseClicked(UIContext context)
    {
        if (this.joining && context.mouseButton == 0)
        {
            if (this.handleJoining(context))
            {
                return true;
            }
        }

        return super.subMouseClicked(context);
    }

    private boolean handleJoining(UIContext context)
    {
        UITab tab = null;

        if (this.container.a.area.isInside(context))
        {
            tab = (UITab) this.container.a;
        }
        else if (this.container.b.area.isInside(context))
        {
            tab = (UITab) this.container.b;
        }

        if (tab != null)
        {
            UITabContainer container = (UITabContainer) this.container.getParent();

            if (this.container == container.a)
            {
                container.replaceA(tab);
            }
            else
            {
                container.replaceB(tab);
            }

            this.container = null;
            this.joining = false;

            return true;
        }

        return false;
    }

    @Override
    public void render(UIContext context)
    {
        super.render(context);

        if (this.joining)
        {
            this.container.a.area.render(context.batcher, this.container.a.area.isInside(context) ? Colors.A50 : Colors.A75);
            this.container.b.area.render(context.batcher, this.container.b.area.isInside(context) ? Colors.A50 : Colors.A75);
        }
    }
}