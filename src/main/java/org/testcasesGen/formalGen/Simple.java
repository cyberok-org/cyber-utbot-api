package org.testcasesGen.formalGen;

// @author(pogrebnoijak)
// @date(19/12/2022)
// @description(This is an design example, it does not show anything worthwhile)

// @tag("if", {"significance": "important", "condition": "difficult"})`
// @tag("overflow")`
// @tag("arithmetic", {"operators": {"+", "*"}, "significance": "insignificant"})
// @tag("simple")`

public class Simple {
    public int fun(int x, int y) {
        int a = x;
        if (x > 0 && x * 2 < 0) {
            a = y;
        }
        return a + x;
    }
}

// @note(found all 3 conditions, works correctly)

/*
----------------------------------------------------------------------------------------------------
path: r0 := @this: org.testcases.formalGen.Simple,
	i0 := @parameter0: int,
	i2 := @parameter1: int,
	i3 = i0,
	if i0 <= 0 goto $i4 = i3 + i0,
	$i4 = i3 + i0
assertions: (Le (int addr: (BVInt32 p_this)) (int addr: Int32 0)),
	(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0)),
	(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486)),
	(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4)),
	(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false),
	(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0))),
	(Le (int (BVInt32 p0)) (int Int32 0)),
	(mkTermArray (array arraysLength : Int32 -> Int32)),
	(mkTermArray (array addrToNumDimensions : Int32 -> Int32)),
	(mkTermArray (array addrToTypeId : Int32 -> Int32))
assumption:
lastStatus: SAT

----------------------------------------------------------------------------------------------------
path: r0 := @this: org.testcases.formalGen.Simple,
	i0 := @parameter0: int,
	i2 := @parameter1: int,
	i3 = i0,
	if i0 <= 0 goto $i4 = i3 + i0,
	$i1 = i0 * 2,
	if $i1 >= 0 goto $i4 = i3 + i0,
	i3 = i2,
	$i4 = i3 + i0
assertions: (Le (int addr: (BVInt32 p_this)) (int addr: Int32 0)),
	(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0)),
	(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486)),
	(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4)),
	(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false),
	(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0))),
	(Gt (int (BVInt32 p0)) (int Int32 0)),
	(Lt (int (Mul (int (BVInt32 p0)) (int Int32 2))) (int Int32 0)),
	(mkTermArray (array arraysLength : Int32 -> Int32)),
	(mkTermArray (array addrToNumDimensions : Int32 -> Int32)),
	(mkTermArray (array addrToTypeId : Int32 -> Int32))
assumption:
lastStatus: SAT

----------------------------------------------------------------------------------------------------
path: r0 := @this: org.testcases.formalGen.Simple,
	i0 := @parameter0: int,
	i2 := @parameter1: int,
	i3 = i0,
	if i0 <= 0 goto $i4 = i3 + i0,
	$i1 = i0 * 2,
	if $i1 >= 0 goto $i4 = i3 + i0,
	$i4 = i3 + i0
assertions: (Le (int addr: (BVInt32 p_this)) (int addr: Int32 0)),
	(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0)),
	(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486)),
	(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4)),
	(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false),
	(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0))),
	(Gt (int (BVInt32 p0)) (int Int32 0)),
	(Ge (int (Mul (int (BVInt32 p0)) (int Int32 2))) (int Int32 0)),
	(mkTermArray (array arraysLength : Int32 -> Int32)),
	(mkTermArray (array addrToNumDimensions : Int32 -> Int32)),
	(mkTermArray (array addrToTypeId : Int32 -> Int32))
assumption:
lastStatus: SAT
 */
