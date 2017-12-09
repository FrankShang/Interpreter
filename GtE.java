import java.util.*;

class GtE extends FunExp
{
	ExpList expList;
	
	GtE(ExpList e)
	{
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		IO.displayln(indent1 + indent1.length() + " <fun exp>");
		IO.displayln(indent2 + indent2.length() + " >");
		expList.printParseTree(indent2);		
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap)
	{
		if ( expList == null || expList.numArgs() != 2 )
		{
			IO.displayln("Error: > operator requires exactly two arguments");
			return null;
		}
		TypeVal exp1Type = expList.firstExp().typeEval(paramMap, paramNumMap);
		TypeVal exp2Type = expList.secondExp().typeEval(paramMap, paramNumMap);

		if ( (exp1Type == TypeVal.Int || exp1Type == TypeVal.Float) && (exp2Type == TypeVal.Int || exp2Type == TypeVal.Float) )
			return TypeVal.Boolean;
		else
		{
			IO.displayln("Type Error: one or both arguments of > have incompatible types");
			return TypeVal.Error;
		}
	}
	
	//change
	Val Eval(HashMap<String,Val> state){
//		Eval( (> B1 ··· Bn), α ) = b1 ∨ (b2 ∨ ··· ∨ (bn−1 ∨ bn)···),   n ≥ 2 
//	   where bi = Eval(Bi, α), 1 ≤ i ≤ n. If at least one bi is ⊥v, the evaluation result is ⊥v.
			return new BoolVal(expList.gtEval(state));
	}
}

//<lt> --> "<"
//<le> --> "<="
//<gt> --> ">"
//<ge> --> ">="
//<eq> --> "="
//<LParen> --> "("
//<RParen> --> ")"