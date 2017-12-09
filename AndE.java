import java.util.*;

class AndE extends FunExp
{
	ExpList expList;
	
	AndE(ExpList e)
	{
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		IO.displayln(indent1 + indent1.length() + " <fun exp>");
		IO.displayln(indent2 + indent2.length() + " and");
		expList.printParseTree(indent2);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap)
	{
		if ( expList == null )
		{
			IO.displayln("Error: and operator requires at least one argument");
			return null;
		}
		TypeVal expListType = expList.typeEvalBool(paramMap, paramNumMap);
		if ( expListType != TypeVal.Error )
			return TypeVal.Boolean;
		else
		{
			IO.displayln("Type Error: some arguments of and operator have incompatible types");
			return TypeVal.Error;
		}	
	}
	
	//change
	Val Eval(HashMap<String,Val> state){
//		Eval( (and B1 ··· Bn), α ) = b1 ∨ (b2 ∨ ··· ∨ (bn−1 ∨ bn)···),   n ≥ 2 
//	   where bi = Eval(Bi, α), 1 ≤ i ≤ n. If at least one bi is ⊥v, the evaluation result is ⊥v.
			return new BoolVal(expList.andEval(state));
	}
}