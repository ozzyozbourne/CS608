const std = @import("std");
const testing = std.testing;
pub fn main() !void {
}


pub fn gaussSummation(num: i32) i32 {
    return @divTrunc((num + 1) * num, 2);
}

test "Gauss Summation Test" {
    
    try testing.expect(gaussSummation(100) == 5050);
    std.debug.print("All tests passed!\n", .{});

}
