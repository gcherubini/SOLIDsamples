import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Depend on abstractions, not on concretions.

class Triplet<T, U, V> {
    private final T first;
    private final U second;
    private final V third;

    public Triplet(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }
    public V getThird() { return third; }
}

enum Relationship {
    PARENT, CHILD
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class RelationshipsDIP {
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }
}

class ResearchDIP {
    public ResearchDIP(RelationshipsDIP relationshipsDIP) {
        List<Triplet<Person, Relationship, Person>> relations = relationshipsDIP.getRelations();
        relations
                .stream()
                .filter(r -> r.getFirst().getName().equals("John") && r.getSecond() == Relationship.PARENT)
                .forEach(r -> System.out.println("John has a child: " + r.getThird().getName()));
    }
}


// Without DiP violation:

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser {
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        // the search dont happen on high level module, it should happens on low level module
        return relations
                .stream()
                .filter(r -> r.getFirst().getName().equals(name) && r.getSecond() == Relationship.PARENT)
                .map(Triplet::getThird)
                .collect(Collectors.toList());
    }
}

class Research { // highlevel - business rules
    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");
        children.forEach(c -> System.out.println("John has a child: " + c.getName()));
    }
}


public class DependencyInversion {
    public static void main(String[] args) {
//        Person parent1 = new Person("John");
//        Person child1 = new Person("Chris");
//        Person child2 = new Person("Matt");
//
//        RelationshipsDIP relationshipsDIP = new RelationshipsDIP();
//        relationshipsDIP.addParentAndChild(parent1, child1);
//        relationshipsDIP.addParentAndChild(parent1, child2);
//
//        new ResearchDIP(relationshipsDIP);

        Person parent1 = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent1, child1);
        relationships.addParentAndChild(parent1, child2);

        new Research(relationships);
    }
}
