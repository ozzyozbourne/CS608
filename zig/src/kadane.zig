//! This module show cases the use of kadane algorithm to find the max subarray in O(n) time and O(1) space
//! Kadane algo is similar to a sliding window technique where we increase the size of the window until the window sum is
//! positive, as soon as the window sum drops to less then we collapse our window

const std = @import("std");
const testing = std.testing;

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
