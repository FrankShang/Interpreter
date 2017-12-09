import java.util.*;

abstract class Exp
{
	abstract void printParseTree(String indent);
	abstract TypeVal typeEval(HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap);
	abstract Val Eval(HashMap<String,Val> state);//change
}
//paramMap {identifier= int, float ,bool}
//paramNumMap{1,2,3 = int, float, bool}