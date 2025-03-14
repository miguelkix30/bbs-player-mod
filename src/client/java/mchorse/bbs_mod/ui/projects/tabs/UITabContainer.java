package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.ScrollDirection;

public class UITabContainer extends UIElement
{
    public final UITabs tabs;
    public UIElement a;
    public UIElement b;
    public final ScrollDirection direction;

    public final UITabResizer tabResizer;

    public UITabContainer(UITabs tabs, UIElement a, UIElement b, ScrollDirection direction)
    {
        this.tabs = tabs;
        this.a = a;
        this.b = b;
        this.direction = direction;

        this.tabResizer = new UITabResizer(this);

        this.a.resetFlex().relative(this);
        this.b.resetFlex().relative(this.a.area);
        this.tabResizer.relative(this.a.area);

        if (direction == ScrollDirection.VERTICAL)
        {
            this.a.w(1F).h(0.5F);
            this.b.y(1F).hTo(this.area, 1F).w(1F);
            this.tabResizer.y(1F, -2).w(1F).h(4);
        }
        else
        {
            this.a.w(0.5F).h(1F);
            this.b.x(1F).wTo(this.area, 1F).h(1F);
            this.tabResizer.x(1F, -2).w(4).h(1F);
        }

        this.add(this.a, this.b, this.tabResizer);
    }
}