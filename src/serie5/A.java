package serie5;

class A{
protected String bla;
public A(){ this .bla = " Hello from A"; }
public void bla (){ System .out . println ( this .bla ); }
public void foo (){ System .out . println ("A.foo "); }
public void bar (){ this .foo (); }
}