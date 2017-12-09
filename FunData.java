// This class is used to record the parameter list and body expression of each function.
// HashMap<String, FunData> constructed by the parser then used by Eval() to evaluate function-call expressions.

class FunData
{
	ParameterList parameterList;
	Exp bodyExp;
}