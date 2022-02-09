
#################################################
# module: cs3430_s22_hw03.py
# YOUR NAME
# YOUR A#
##################################################

import numpy as np
import matplotlib.pyplot as plt
from cs3430_s22_hw01 import gje

## =============== Problem 01 ==================

def line_ip(line1, line2):

    x1, y1, b1 = float(line1[0]), float(line1[1]), float(line1[2])
    x2, y2, b2 = float(line2[0]), float(line2[1]), float(line2[2])

    A = np.array([[x1, y1],
                  [x2, y2]])
    b = np.array([[b1],
                  [b2]])

    return gje(A, b)

### This is the same as the static method
### cs3430_s22_hw03_uts.check_line_ip(line1, line2, ip, err=0.0001)
### in cs3430_s22_hw03_uts.py. This is for your
### convenience.
def check_line_ip(line1, line2, ip, err=0.0001):
    assert ip is not None
    A1, B1, C1 = line1
    A2, B2, C2 = line2
    x = ip[0, 0]
    y = ip[1, 0]
    assert abs((A1*x + B1*y) - C1) <= err
    assert abs((A2*x + B2*y) - C2) <= err
    return True

## Be careful not to compute the same intersection twice.
## In other words, if l1 and l2 are two lines, the
## intersection point b/w l1 and l2 is the same as the
## intersection point b/w l2 and l1. Computing duplicate
## intersections will not render the required computation
## incorrect, but it will make it more efficient.
def find_line_ips(lines):
    ips = []

    for i in range(0, len(lines)):
        for j in range(i + 1, len(lines)):
            ips.append(line_ip(lines[i], lines[j]))

    return ips

## For problems 2.1 and 2.2
def max_obj_fun(f, cps):
    """
    maximize obj fun f on corner points cps
    """
    max = ()
    for point in cps:
        current = f(point[0], point[1])
        if len(max) == 0:
            max = (point, float(current))
        elif current > max[1]:
            max = (point, float(current))
    
    return max

## =============== Problem 02 ==================

### Graphing constraints to the Ted's Toys problem we worked
### out in CS3430: S22: Lecture 05.
def plot_teds_constraints():
    ### plastic constraint: 4x + 3y <= 480
    def plastic_constraint(x): return -(4/3.0)*x + 160.0
    ### steel constraints: 3x + 6y <= 720
    def steel_constraint(x): return -0.5*x + 120.0
    xvals  = np.linspace(0, 160, 10000)
    yvals1 = np.array([plastic_constraint(x) for x in xvals])
    yvals2 = np.array([steel_constraint(x) for x in xvals])
    fig1  = plt.figure(1)
    fig1.suptitle('Ted\'s Toys Problem')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.ylim([-5, 160])
    plt.xlim([-5, 160])
    ## x = 0
    x1, y1 = [0, 0], [0, 160]
    ## y = 0
    x2, y2 = [0, 160], [0, 0]
    plt.grid()
    plt.plot(xvals, yvals1, label='4x+3y=480', c='red')
    plt.plot(xvals, yvals2, label='3x+6y=720', c='blue')
    plt.plot(x1, y1, label='x=0', c='green')
    plt.plot(x2, y2, label='y=0', c='yellow')
    plt.legend(loc='best')
    plt.show()

def teds_problem():
    red_line    = (4, 3, 480)
    blue_line   = (3, 6, 720)
    green_line  = (1, 0, 0)
    yellow_line = (0, 1, 0)

    cp1 = line_ip(green_line, yellow_line)
    cp2 = line_ip(green_line, blue_line)
    cp3 = line_ip(blue_line, red_line)
    cp4 = line_ip(red_line, yellow_line)

    obj_fun = lambda x, y: 5.0*x + 4.0*y

    rslt = max_obj_fun(obj_fun, [cp1, cp2, cp3, cp4])

    x = rslt[0][0][0]
    y = rslt[0][1][0]
    p = rslt[1]

    print('num cars   = {}'.format(x))
    print('num trucks = {}'.format(y))
    print('profit     = {}'.format(p))

    return x, y, p

def plot_2_1_constraints():
    def first_constraint(x): return -(x) + 3
    def second_constraint(x): return (3 * x) + 1
    xvals = np.linspace(0, 10, 10000)
    yvals1 = np.array([first_constraint(x) for x in xvals])
    yvals2 = np.array([second_constraint(x) for x in xvals])
    fig1 = plt.figure(1)
    fig1.suptitle('Problem 2.1')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.ylim([-2,10])
    plt.xlim([-2,10])
    x1, y1 = [2,2], [-2, 10]
    x2, y2 = [0,10], [0,0]
    plt.grid()
    plt.plot(xvals, yvals1, label='eq1', c='red')
    plt.plot(xvals, yvals2, label='eq2', c='blue')
    plt.plot(x1, y1, label='x=2', c='green')
    plt.plot(x2, y2, label='y=0', c='yellow')
    plt.legend(loc='best')
    plt.show()


def problem_2_1():
    red_line = (1, 1, 3)
    blue_line = (3, -1, -1)
    green_line = (1, 0, 2) 

    cp1 = line_ip(green_line, blue_line)
    cp2 = line_ip(green_line, red_line)
    cp3 = line_ip(red_line, blue_line)

    obj_fun = lambda x, y: 3.0*x + y
    result = max_obj_fun(obj_fun, [cp1, cp2, cp3])
    x = result[0][0][0]
    y = result[0][1][0]
    p = result[1]

    return x, y, p

def plot_2_2_constraints():
    def first_constraint(x): return (-(.5) * x) + 3
    def second_constraint(x): return x + 4
    def third_constraint(x): return -(2) * x + 8

    xvals = np.linspace(0, 40, 10000)
    yvals1 = np.array([first_constraint(x) for x in xvals])
    yvals2 = np.array([second_constraint(x) for x in xvals])
    yvals3 = np.array([third_constraint(x) for x in xvals])
    
    fig1 = plt.figure(1)
    fig1.suptitle('Problem 2.2')
    plt.xlabel('x')
    plt.ylabel('y')

    plt.ylim([-2,10])
    plt.xlim([-2,10])
    x1, y1 = [0,0], [0, 10]
    x2, y2 = [0,10], [0,0]

    plt.grid()
    plt.plot(xvals, yvals1, label='eq1', c='red')
    plt.plot(xvals, yvals2, label='eq2', c='blue')
    plt.plot(xvals, yvals3, label='eq3', c='purple')
    plt.plot(x1, y1, label='x=0', c='green')
    plt.plot(x2, y2, label='y=0', c='yellow')
    plt.legend(loc='best')
    plt.show()

def problem_2_2():
    red_line = (1, 2, 6)
    blue_line = (1, -1, -4)
    purple_line = (2, 1, 8)
    green_line = (1, 0, 0)
    yellow_line = (0, 1, 0)

    cp1 = line_ip(green_line, blue_line)
    cp2 = line_ip(green_line, red_line)
    cp3 = line_ip(red_line, purple_line)
    cp4 = line_ip(blue_line, purple_line)

    obj_fun = lambda x, y: x + y
    result = max_obj_fun(obj_fun, [cp1, cp2, cp3, cp4])
    x = result[0][0][0]
    y = result[0][1][0]
    p = result[1]

    return x, y, p

## For problem 2.3
def min_obj_fun(f, cps):
    """
    minimize obj fun f on corner points cps
    """
    min = ()
    for point in cps:
        current = f(point[0], point[1])
        if len(min) == 0:
            min = (point, float(current))
        elif current < min[1]:
            min = (point, float(current))
    
    return min

def plot_2_3_constraints():
    def first_constraint(x): return - (4 * x) + 450
    def second_constraint(x): return (-(0.5) * x) + 100
    xvals = np.linspace(0, 600, 10000)
    yvals1 = np.array([first_constraint(x) for x in xvals])
    yvals2 = np.array([second_constraint(x) for x in xvals])
    fig1 = plt.figure(1)
    fig1.suptitle('Problem 2.3')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.ylim([-30,600])
    plt.xlim([-30,600])
    x1, y1 = [0,0], [0, 600]
    x2, y2 = [0,600], [0,0]
    plt.grid()
    plt.plot(xvals, yvals1, label='eq1', c='red')
    plt.plot(xvals, yvals2, label='eq2', c='blue')
    plt.plot(x1, y1, label='x=2', c='green')
    plt.plot(x2, y2, label='y=0', c='yellow')
    plt.legend(loc='best')
    plt.show()

def problem_2_3():
    red_line = (0.8, 0.2, 90)
    blue_line = (3, 6, 600)
    green_line = (1, 0, 0)
    yellow_line = (0, 1, 0)

    cp1 = line_ip(green_line, blue_line)
    cp2 = line_ip(green_line, yellow_line)
    cp3 = line_ip(yellow_line, red_line)
    cp4 = line_ip(red_line, blue_line)

    obj_fun = lambda x, y: (4 * x) + (5 * y)
    result = max_obj_fun(obj_fun, [cp1, cp2, cp3, cp4])
    x = result[0][0][0]
    y = result[0][1][0]
    p = result[1]

    return x, y, p




    
    
    


                    


    
