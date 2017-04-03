package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManageImports
{

    private SessionFactory factory;

    public ManageImports(SessionFactory factory) {this.factory = factory;}

}
