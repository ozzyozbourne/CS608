//! This module show cases the use of kadane algorithm to find the max subarray in O(n) time and O(1) space
//! Kadane algo is similar to a sliding window technique where we increase the size of the window until the window sum is
//! positive, as soon as the window sum drops to less then we collapse our window

const std = @import("std");
const testing = std.testing;
const Res = struct { l: usize, h: usize, sum: i32 };

fn max(a: i32, b: i32) i32 {
    return if (a > b) a else b;
}

pub fn maxSubArraySum(arr: []const i32) i32 {
    var max_sum, var curr_sum: i32 = .{ arr[0], 0 };
    for (arr) |n| {
        curr_sum = max(curr_sum, 0) + n;
        max_sum = max(curr_sum, max_sum);
    }
    return max_sum;
}

pub fn maxSubArrayLocation(arr: []const i32) struct { l: i32, r: i32 } {
    var max_sum, var curr_sum: i32, var maxl: i32, var maxr: i32, var l: i32 = .{ arr[0], 0, 0, 0, 0 };

    for (0..arr.len) |r| {
        if (curr_sum < 0) {
            curr_sum = 0;
            l = @intCast(r);
        }
        curr_sum += arr[r];
        if (curr_sum > max_sum) {
            max_sum = curr_sum;
            maxl = l;
            maxr = @intCast(r);
        }
    }

    return .{ .l = maxl, .r = maxr };
}

pub fn maxSubArray(l: usize, h: usize, arr: []const i32) Res {
    // bases case
    if (l == h) return Res{ .l = l, .h = h, .sum = arr[l] };
    // calculate the mid for current l and h
    const mid: usize = l + @divTrunc(h - l, 2);
    //calcute the current leftmax and right max the max from mid to low and mid to high
    const leftMax = maxSubArray(l, mid, arr);
    const rightMax = maxSubArray(mid + 1, h, arr);
    const maxcrossing = maxCrossing(l, mid, h, arr);

    const maxofleftandright = if (leftMax.sum > rightMax.sum) leftMax else rightMax;
    return if (maxofleftandright.sum > maxcrossing.sum) maxofleftandright else maxcrossing;
}

fn maxCrossing(l: usize, mid: usize, h: usize, arr: []const i32) Res {
    
    var sum: i32, var lsum: i32, var maxleft: usize = .{ 0, std.math.minInt(i32), mid };

    var iter: i32 = @intCast(mid);
    const stop: i32 = @intCast(l);
    
    while(iter >= stop) {
        sum += arr[@intCast(iter)];
        if (sum > lsum) {
            lsum = sum;
            maxleft = @intCast(iter);
        }
        iter -= 1;
    }

    sum, var rsum: i32, var maxright: usize = .{ 0, std.math.minInt(i32), mid + 1 };

    for ((mid + 1)..(h + 1)) |i| {
        sum += arr[i];
        if (sum > rsum) {
            rsum = sum;
            maxright = i;
        }
    }

    return Res{ .l = maxleft, .h = maxright, .sum = lsum + rsum };
}

test "Divide and conquer Algorighm for max subarray sum" {
    const TestCase = struct {
        input: []const i32,
        expected: Res,
    };
    const testcases = [_]TestCase{
        TestCase{ .input = &[_]i32{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }, .expected = Res{ .l = 3, .h = 6, .sum = 6 } },
        TestCase{ .input = &[_]i32{1},                               .expected = Res{ .l = 0, .h = 0, .sum = 1 } },
        TestCase{ .input = &[_]i32{ 5, 4, -1, 7, 8 },                .expected = Res{ .l = 0, .h = 4, .sum = 23 } },
    };

    for (testcases) |testcase| {
        const res = maxSubArray(0, @intCast(testcase.input.len - 1), testcase.input);
//        std.debug.print("{} {} {}\n", .{res.l, res.h, res.sum});
        try testing.expect(
            res.l == testcase.expected.l and 
            res.h == testcase.expected.h and 
            res.sum == testcase.expected.sum
        );
    }

    std.debug.print("All test cases passed!\n", .{});
}

test "Kadane Algorighm for max subarray sum" {
    const TestCase = struct {
        input: []const i32,
        expected: i32,
    };
    const testcases = [_]TestCase{
        TestCase{ .input = &[_]i32{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }, .expected = 6 },
        TestCase{ .input = &[_]i32{1}, .expected = 1 },
        TestCase{ .input = &[_]i32{ 5, 4, -1, 7, 8 }, .expected = 23 },
    };

    for (testcases) |testcase| {
        const res = maxSubArraySum(testcase.input);
        try testing.expect(res == testcase.expected);
    }

    std.debug.print("All test cases passed!\n", .{});
}

test "Kadane algo using two pointer" {
    const TestCase = struct {
        input: []const i32,
        expected: struct { l: i32, r: i32 },
    };
    const testcases = [_]TestCase{
        TestCase{ .input = &[_]i32{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }, .expected = .{ .l = 3, .r = 6 } },
        TestCase{ .input = &[_]i32{1}, .expected = .{ .l = 0, .r = 0 } },
        TestCase{ .input = &[_]i32{ 5, 4, -1, 7, 8 }, .expected = .{ .l = 0, .r = 4 } },
    };

    for (testcases) |testcase| {
        const res = maxSubArrayLocation(testcase.input);
        try testing.expect(res.l == testcase.expected.l);
        try testing.expect(res.r == testcase.expected.r);
    }

    std.debug.print("All test cases passed!\n", .{});
}
