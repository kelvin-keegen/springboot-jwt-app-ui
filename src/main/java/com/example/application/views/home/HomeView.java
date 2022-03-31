package com.example.application.views.home;

import com.example.application.views.passwordchange.PasswordChangeView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.ls.LSOutput;

@PageTitle("Home")
@Route(value = "home")
@JsModule("./recipe/copytoclipboard/copytoclipboard.js")
@Slf4j
public class HomeView extends AppLayout {

    public HomeView() {

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Home");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title);
        setContent(SetRootDefaultContent());
    }

    private Tabs getTabs() {

        Tabs tabs = new Tabs();
        tabs.add(
                createTab(VaadinIcon.HOME,"Home",HomeView.class),
                createTab(VaadinIcon.SERVER, "credentials",CredentialsView.class),
                createTab(VaadinIcon.UNLOCK,"Password Change", PasswordChangeView.class)

        );
        tabs.setOrientation(Tabs.Orientation.VERTICAL);

        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> navigationTarget) {

        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(navigationTarget);

        return new Tab(link);

    }

    public VerticalLayout SetRootDefaultContent() {

        VerticalLayout verticalLayout = new VerticalLayout();

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("300px");

        verticalLayout.add(

                img,
                new H5("This view has been left like this intentionally. :)"),
                VaadinIcon.SMILEY_O.create()
        );

        verticalLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        return verticalLayout;
    }

}
