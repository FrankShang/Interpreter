import java.util.*;

class ExpList {
	Exp exp;
	ExpList expList; // expList is null at the end of the list.

	ExpList(Exp e, ExpList el) {
		exp = e;
		expList = el;
	}

	Exp firstExp() {
		return exp;
	}

	Exp secondExp() {
		return expList.firstExp();
	}

	ExpList tailExpList() {
		return expList;
	}

	int numArgs() {
		if (expList == null)
			return 1;
		else
			return 1 + expList.numArgs();
	}

	void printParseTree(String indent) {
		exp.printParseTree(indent);
		if (expList != null)
			expList.printParseTree(indent);
	}

	TypeVal typeEvalArith(HashMap<String, TypeVal> paramMap, HashMap<Integer, TypeVal> paramNumMap) {
		TypeVal expType = exp.typeEval(paramMap, paramNumMap);

		if (expList == null)
			return expType;
		else {
			TypeVal expListType = expList.typeEvalArith(paramMap, paramNumMap);
			if (expType == TypeVal.Int && expListType == TypeVal.Int)
				return TypeVal.Int;
			else if (expType == TypeVal.Float && expListType == TypeVal.Float)
				return TypeVal.Float;
			else
				return TypeVal.Error;
		}
	}

	TypeVal typeEvalBool(HashMap<String, TypeVal> paramMap, HashMap<Integer, TypeVal> paramNumMap) {
		TypeVal expType = exp.typeEval(paramMap, paramNumMap);

		if (expList == null)
			return expType;
		else {
			TypeVal expListType = expList.typeEvalBool(paramMap, paramNumMap);
			if (expType == TypeVal.Boolean && expListType == TypeVal.Boolean)
				return TypeVal.Boolean;
			else
				return TypeVal.Error;
		}
	}

	TypeVal typeEvalFunCall(HashMap<String, TypeVal> paramMap, HashMap<Integer, TypeVal> paramNumMap, int i,
			HashMap<Integer, TypeVal> paramNumTypeMap) {
		TypeVal expType = exp.typeEval(paramMap, paramNumMap);
		TypeVal paramType = paramNumTypeMap.get(i);

		if (expList == null) {
			if (expType == paramType)
				return TypeVal.Correct;
			else {
				IO.displayln("Type Error: incompatible type for parameter # " + i);
				return TypeVal.Error;
			}
		} else {
			TypeVal expListType = expList.typeEvalFunCall(paramMap, paramNumMap, i + 1, paramNumTypeMap);

			if (expType == paramType && expListType == TypeVal.Correct)
				return TypeVal.Correct;
			else if (expType != paramType) {
				IO.displayln("Type Error: incompatible type for parameter # " + i);
				return TypeVal.Error;
			} else
				return TypeVal.Error;
		}
	}

	// change
	Val Eval(HashMap<String, Val> state) {
		if (exp == null && expList == null)
			return null;
		Val expVal = exp.Eval(state);

		if (expList == null) {
			return expVal.cloneVal();
		}
		Val expListVal = expList.Eval(state);
		if (expVal == expListVal) {
			return expVal.cloneVal();
		} else {
			return null;
		}
	}

	// + AddEval
	float AddEval(HashMap<String, Val> state) {
		if (expList == null)
			return exp.Eval(state).floatVal();
		else
			return exp.Eval(state).floatVal() + expList.AddEval(state);
	}

	// - SubEval
	float SubEval(HashMap<String, Val> state) {
		if (expList == null)
			return exp.Eval(state).floatVal();
		else
			return exp.Eval(state).floatVal() - expList.SubEval(state);
	}

	// * MulEval
	float MulEval(HashMap<String, Val> state) {
		// Val expListVal;
		if (expList == null)
			return exp.Eval(state).floatVal();
		else
			return exp.Eval(state).floatVal() * expList.MulEval(state);

	}

	// '/' SubEval
	float DivEval(HashMap<String, Val> state) {
		if (expList == null)
			return exp.Eval(state).floatVal();
		else {
			if (expList.DivEval(state) != 0.0f) // {
				return exp.Eval(state).floatVal() / expList.DivEval(state);
			else
				System.out.print(" Error: division by ");
			return 0.0f;

		}
	}

	boolean ltEval(HashMap<String, Val> state) {
		return firstExp().Eval(state).floatVal() < secondExp().Eval(state).floatVal();
	}

	boolean leEval(HashMap<String, Val> state) {
		return firstExp().Eval(state).floatVal() <= secondExp().Eval(state).floatVal();
	}

	boolean gtEval(HashMap<String, Val> state) {
		return firstExp().Eval(state).floatVal() > secondExp().Eval(state).floatVal();
	}

	boolean geEval(HashMap<String, Val> state) {
		return firstExp().Eval(state).floatVal() >= secondExp().Eval(state).floatVal();
	}

	boolean eqEval(HashMap<String, Val> state) {
		return firstExp().Eval(state).floatVal() == secondExp().Eval(state).floatVal();

	}

	boolean andEval(HashMap<String, Val> state) {
		return (firstExp().Eval(state).floatVal() != 0.0f) && (tailExpList().Eval(state).floatVal() != 0.0f);
	}

	boolean orEval(HashMap<String, Val> state) {
		return (firstExp().Eval(state).floatVal() != 0.0f) || (tailExpList().Eval(state).floatVal() != 0.0f);
	}

	boolean notEval(HashMap<String, Val> state) {
		return !(exp.Eval(state).floatVal() != 0.0f);
	}

	void statePut(HashMap<String, Val> state, String fName, int count) {
		// count = 1;
		//System.out.println(TypeChecker.paramFunNameNumNameMap.get(fName).toString()+"-------paramNumName location : param");
		state.put(TypeChecker.paramFunNameNumNameMap.get(fName).get((Integer) count), firstExp().Eval(state));
		//System.out.println(state.toString() + "------state");
		if (tailExpList() != null) {
			tailExpList().statePut(state, fName, count + 1);
		}
	}

	Val funCallEval(HashMap<String, Val> state, String fName) {
		statePut(state, fName, 1);
		return Parser.funMap.get(fName).bodyExp.Eval(state);
	}

}