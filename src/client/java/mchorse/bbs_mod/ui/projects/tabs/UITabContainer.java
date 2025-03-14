package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.ScrollDirection;

public class UITabContainer extends UIElement
{
    public final UITabs tabs;
    public UIElement a;
    public UIElement b;
    public ScrollDirection direction;

    public final UITabResizer tabResizer;

    public UITabContainer(UITabs tabs, UIElement a, UIElement b, ScrollDirection direction)
    {
        this.tabs = tabs;
        this.a = a;
        this.b = b;
        this.direction = direction;
        this.tabResizer = new UITabResizer(this);

        this.refreshFlex(0.5F, 0.5F);
        this.add(this.a, this.b, this.tabResizer);
    }

    public void refreshFlex()
    {
        this.refreshFlex(this.a.getFlex().w.value, this.a.getFlex().h.value);
    }

    public void refreshFlex(float aw, float ah)
    {
        this.a.resetFlex().relative(this);
        this.b.resetFlex().relative(this.a.area);
        this.tabResizer.resetFlex().relative(this.a.area);

        if (this.direction == ScrollDirection.VERTICAL)
        {
            this.a.w(1F).h(ah);
            this.b.y(1F).hTo(this.area, 1F).w(1F);
            this.tabResizer.y(1F, -2).w(1F).h(4);
        }
        else
        {
            this.a.w(aw).h(1F);
            this.b.x(1F).wTo(this.area, 1F).h(1F);
            this.tabResizer.x(1F, -2).w(4).h(1F);
        }
    }

    public void flipOrientation()
    {
        this.direction = this.direction == ScrollDirection.VERTICAL ? ScrollDirection.HORIZONTAL : ScrollDirection.VERTICAL;

        this.refreshFlex(this.a.getFlex().h.value, this.a.getFlex().w.value);
        this.tabs.resize();
    }

    public void replaceA(UIElement newContainer)
    {
        float aw = this.a.getFlex().w.value;
        float ah = this.a.getFlex().h.value;

        this.a.removeFromParent();
        this.prepend(newContainer);

        this.a = newContainer;

        this.refreshFlex(aw, ah);
        this.tabs.resize();
    }

    public void replaceB(UIElement newContainer)
    {
        this.b.removeFromParent();
        this.addAfter(this.a, newContainer);

        this.b = newContainer;

        this.refreshFlex();
        this.tabs.resize();
    }
}