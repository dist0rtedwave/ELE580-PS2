package proof.model;

import java.util.ArrayList;

abstract public class Clause {
   abstract void print();
   abstract ArrayList<Literal> getLiterals();
}
